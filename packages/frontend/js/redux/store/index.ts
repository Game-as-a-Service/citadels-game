import { configureStore } from '@reduxjs/toolkit'
import mainReducer from '../slice/mainSlice'
import roomReducer from '../slice/roomSlice'

export const store = configureStore({
  reducer: {
    main: mainReducer,
    room: roomReducer
  }
})

// Infer the `RootState` and `AppDispatch` types from the store itself
export type RootState = ReturnType<typeof store.getState>
// Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}
export type AppDispatch = typeof store.dispatch
