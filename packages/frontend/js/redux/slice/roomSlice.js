import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import * as api from '../../api'

export const getRoomList = createAsyncThunk(
  'room/getRoomList',
  async (payload, { rejectWithValue }) => {
    try {
      const res = await api.getRoomList(payload)
      console.log(res)
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
    data: null
  },
  reducers: {
    clearData: (state, action) => {
      state.data = null
    }
  },
  extraReducers: (builder) => {
    builder.addCase(getRoomList.fulfilled, (state, action) => {
      state.data = action.payload.rooms
      return state
    })
  }
})

export const { clearData } = roomSlice.actions

export default roomSlice.reducer
