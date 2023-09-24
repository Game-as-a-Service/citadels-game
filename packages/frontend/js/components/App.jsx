import { Routes, Route, Navigate } from 'react-router-dom'
import RoomList from './RoomList'
import Game from './Game'

const App = () => {
  return (
    <>
      <Routes>
        <Route path='/' element={<Navigate to='/rooms' />} />
        <Route path='/rooms' element={<RoomList />} />
        <Route path='/game/:roomId' element={<Game />} />
      </Routes>
    </>
  )
}
export default App
