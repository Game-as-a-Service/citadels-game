import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import * as api from '../../api'
import { RoomList } from '../../api'

export const getRoomList = createAsyncThunk(
  'room/getRoomList',
  async (payload, { rejectWithValue }) => {
    try {
      const res = await api.getRoomList()
      if (res) {
        return res.data
      }
    } catch (error) {
      return rejectWithValue(error)
    }
  }
)

interface RoomState {
  data: RoomList | null
  loading: boolean
}

const initialState: RoomState = {
  data: null,
  loading: false
}

const roomSlice = createSlice({
  name: 'room',
  initialState,
  reducers: {
    clearData: (state, action) => {
      state.data = null
    }
  },
  extraReducers: (builder) => {
    builder
      .addCase(getRoomList.pending, (state, action) => {
        state.loading = true
        return state
      })
      .addCase(getRoomList.fulfilled, (state, action) => {
        if (action.payload) state.data = action.payload
        state.loading = false
        return state
      })
      .addCase(getRoomList.rejected, (state, action) => {
        state.loading = false
        return state
      })
  }
})

export const { clearData } = roomSlice.actions

export default roomSlice.reducer
