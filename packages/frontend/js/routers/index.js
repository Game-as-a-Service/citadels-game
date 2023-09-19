import { BrowserRouter, Routes, Route } from 'react-router-dom'
import App from '../components/App'
import Login from '../components/Login'
import { AuthContextProvider } from './contexts/AuthContext'

const Routers = () => {
  return (
    <BrowserRouter>
      <AuthContextProvider>
        <Routes>
          <Route path='/login' element={<Login />} />
          <Route path='/*' element={<App />} />
        </Routes>
      </AuthContextProvider>
    </BrowserRouter>
  )
}

export default Routers
