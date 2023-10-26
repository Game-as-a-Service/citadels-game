import { configureStore } from '@reduxjs/toolkit'
import mainReducer from '../slice/mainSlice'
import roomReducer from '../slice/roomSlice'

export default configureStore({
  reducer: {
    main: mainReducer,
    room: roomReducer
  }
})
