import { BrowserRouter, Routes, Route } from 'react-router-dom'
import App from '../components/App'
import Login from '../components/Login'

const Routers = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/login' element={<Login />} />
        <Route path='/*' element={<App />} />
      </Routes>
    </BrowserRouter>
  )
}

export default Routers
