import { configureStore } from '@reduxjs/toolkit'
import mainReducer from '../slice/mainSlice'

export default configureStore({
  reducer: {
    main: mainReducer
  }
})
