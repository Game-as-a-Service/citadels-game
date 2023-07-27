import { Routes, Route, Navigate } from 'react-router-dom'
import RoomList from './RoomList'
import Game from './Game'

import getAPI from '../api/getAPI'

const App = () => {
  const { data } = getAPI()
  return (
    <>
      {/* <div>title:{data.msg}</div> */}
      <Routes>
        <Route path='/' element={<Navigate to='/rooms' />} />
        <Route path='/rooms' element={<RoomList />} />
        <Route path='/game' element={<Game />} />
      </Routes>
    </>
  )
}
export default App
