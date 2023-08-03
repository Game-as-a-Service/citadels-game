import axiosInstance from '../common/axiosInstance'

const url =
  process.env.NODE_ENV === 'development'
    ? 'https://001f08b9-acb7-4c3a-a54f-a9254b7e8e55.mock.pstmn.io'
    : ''
const axios = axiosInstance(url)

export const getRoomList = (payload) => {
  return axios.get('/games', payload)
}

export const createRoom = (payload) => {
  return axios.post('/createroom', payload)
}
