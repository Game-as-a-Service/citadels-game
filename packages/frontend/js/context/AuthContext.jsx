import { createContext, useState, useContext } from 'react'
import { useNavigate } from 'react-router-dom'

const AuthContext = createContext({})
export default AuthContext

export const AuthContextProvider = ({ children }) => {
  const navigate = useNavigate()
  // 預設為未登入狀態
  const unAuth = {
    userId: '',
    userName: '',
    userPortrait: ''
  }

  let initAuth = { ...unAuth }

  const userId = localStorage.getItem('userId')
  const userName = localStorage.getItem('userName')
  const userPortrait = localStorage.getItem('userPortrait')

  try {
    if (userId && userName) {
      initAuth = {
        userId,
        userName,
        userPortrait
      }
    }
  } catch (ex) {}

  const [myAuth, setMyAuth] = useState(initAuth)

  const logout = () => {
    localStorage.removeItem('myAuth')
    setMyAuth(unAuth)
    navigate('/login')
  }

  return (
    <AuthContext.Provider value={{ myAuth, setMyAuth, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

AuthContextProvider.propTypes = {
  children: PropTypes.node.isRequired
}

export const useAuth = () => {
  const context = useContext(AuthContext)
  if (!context) {
    throw new Error('useUser must be used within a UserProvider')
  }
  return context
}
