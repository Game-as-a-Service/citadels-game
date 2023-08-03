import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import * as api from '../../api'

export const getRoomList = createAsyncThunk(
  'room/getRoomList',
  async (payload, { rejectWithValue }) => {
    try {
      const res = await api.getRoomList(payload)
      if (res.status === 'OK') {
        return res
      } else {
        return rejectWithValue(res.msg)
      }
    } catch (error) {
      return rejectWithValue(error)
    }
  }
)

const roomSlice = createSlice({
  name: 'room',
  initialState: {
    data: null,
    loading: false
  },
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
        state.data = action.payload
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
