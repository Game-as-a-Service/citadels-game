import axiosInstance from '../common/axiosInstance'

const url =
  process.env.NODE_ENV === 'development'
    ? 'https://be2f8ec7-1684-4893-862f-1be25a174c3b.mock.pstmn.io'
    : ''
const axios = axiosInstance(url)

export const getRoomList = (payload) => {
  return axios.get('/games', payload)
}
