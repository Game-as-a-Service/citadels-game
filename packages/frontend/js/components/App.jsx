import { Routes, Route, Navigate } from 'react-router-dom'
import RoomList from './RoomList'
import Game from './Game'
import getAPI from '../api/getAPI'
import { UserProvider } from '../contexts/UserContext'
const App = () => {
  const { data } = getAPI()
  return (
    <>
      {/* <div>title:{data.msg}</div> */}
      <UserProvider>
        <Routes>
          <Route path='/' element={<Navigate to='/rooms' />} />
          <Route path='/rooms' element={<RoomList />} />
          <Route path='/game/:roomId' element={<Game />} />
        </Routes>
      </UserProvider>
    </>
  )
}
export default App
