export type CreateRoom = {
  createTime: string
  status: string
  msg: string
  rooms: Record<string, UpdatedRooms>
}

export type UpdatedRooms = {
  roomId: string
  roomName: string
  holderId: string
  holderName: string
  users: Array<{
    userId: string
    userName: string
    userImage: string
  }>
  status: string
  totalUsers: number
}

export type SpecificRoom = {
  searchTime: string
  status: string
  msg: string
  rooms: Record<string, TimedRooms>
}

export type TimedRooms = {
  roomId: string
  roomName: string
  createTime: string
  status: string
  holderName: string
  holderId: string
  totalUsers: number
  users: Array<{
    userId: string
    userName: string
    userImage: string
  }>
}
