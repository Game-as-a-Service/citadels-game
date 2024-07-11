import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import * as api from '../../api'

const initialState = {
  data: null
}

export const getGame = createAsyncThunk(
  'gameStatus/getGame',
  async (gameId: string, { rejectWithValue }) => {
    try {
      const res = await api.getGame(gameId)
      if (res) {
        return res.data
      }
    } catch (error) {
      return rejectWithValue(error)
    }
  }
)

const gameStatus = createSlice({
  name: 'gameStatus',
  initialState,
  reducers: {
    clearData: (state, action) => {
      state.data = null
    }
  },
  extraReducers: (builder) => {
    builder
      .addCase(getGame.fulfilled, (state, action) => {
        state.data = action.payload
        return state
      })
      .addCase(getGame.rejected, (state, action) => {
        state.data = null
        return state
      })
  }
})

export const { clearData } = gameStatus.actions

export default gameStatus.reducer
