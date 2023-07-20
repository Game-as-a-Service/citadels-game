import { BrowserRouter, Routes, Route } from 'react-router-dom'
import App from '../components/App'
import Login from '../components/Login'
import Game from '../components/Game'

const Routers = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<App />} />
        <Route path='/login' element={<Login />} />
        <Route path='/game' element={<Game />} />
      </Routes>
    </BrowserRouter>
  )
}

export default Routers
