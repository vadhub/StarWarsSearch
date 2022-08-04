package com.vad.starwarssearch.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.data.entity.CharacterRemoteKeys
import com.vad.starwarssearch.data.local.AppDatabase
import com.vad.starwarssearch.data.remote.CharacterApi

@ExperimentalPagingApi
class CharacterRemoteMediator(
    private val characterApi: CharacterApi,
    private val db: AppDatabase
) : RemoteMediator<Int, Character>() {

    private val characterDao = db.characterDao()
    private val characterRemoteKeysDao = db.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): RemoteMediator.MediatorResult {

        val currentPage = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextPage?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(remoteKeys != null)
                prevPage
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(remoteKeys != null)
                nextPage
            }
        }

        val response = characterApi.getAllCharacter(currentPage)

    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Character>): CharacterRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                characterRemoteKeysDao.getRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Character>): CharacterRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                characterRemoteKeysDao.getRemoteKeys(character.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Character>): CharacterRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                characterRemoteKeysDao.getRemoteKeys(character.id)
            }
    }


}