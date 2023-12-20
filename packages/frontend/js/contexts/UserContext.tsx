// src/UserContext.tsx
import React, { createContext, useContext, useState, useEffect } from 'react'

interface User {
  userId: string
  userName: string
  userImage: string
}

interface UserContextType {
  user: User | null
}

interface UserProviderProps {
  children: React.ReactNode
}

const UserContext = createContext<UserContextType | undefined>(undefined)

export const UserProvider: React.FC<UserProviderProps> = ({ children }) => {

  const [user, setUser] = useState<User | null>(null)

  useEffect(() => {
    const fetchUser = async () => {
      setUser({
        ...user,
        userId: localStorage.getItem('userId') ?? '',
        userName: localStorage.getItem('userName') ?? '',
        userImage: localStorage.getItem('userImage') ?? ''
      })
    }

    fetchUser()
  }, [])

  return (
    <UserContext.Provider value={{ user }}>{children}</UserContext.Provider>
  )
}

export const useUser = () => {
  const context = useContext(UserContext)
  if (context === undefined) {
    throw new Error('useUser must be used within a UserProvider')
  }
  return context
}
