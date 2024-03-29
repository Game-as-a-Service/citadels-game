import axiosInstance from '../common/axiosInstance'
import { CreateRoom, SpecificRoom } from '../common/types'
type Domain = {
  development: string
  production: string
}
const domain: Domain = {
  development: 'https://001f08b9-acb7-4c3a-a54f-a9254b7e8e55.mock.pstmn.io',
  production: 'https://104.155.239.183'
}

const env = process.env.NODE_ENV === 'production' ? 'production' : 'development'

const axios = axiosInstance(domain[env])

export type RoomList = {
  totalRooms: number
  status: string
  msg: string
  rooms: Array<RoomInfo>
}

type RoomInfo = {
  roomId: string
  gameName: string
  createTime: string
  status: string
  holderName: string
  holderId: string
  totalPlayers: number
  users: Array<player>
}

type player = {
  playerId: string
  playerName: string
}

export const getRoomList = () => {
  return axios.get<RoomList>('/rooms')
}

export const createRoom = (payload: Object) => {
  return axios.post<CreateRoom>('/api/citadels/room', payload)
}

export const getSpecificRoom = (payload: String) => {
  return axios.get<SpecificRoom>(`/api/citadels/rooms/${payload}`)
}

export const leaveRoom = (payload: Object, roomId: string) => {
  return axios.post(`/rooms/${roomId}:leave`, payload)
}
