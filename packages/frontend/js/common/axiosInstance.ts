import axios from 'axios'

export default (baseUrl: string) => {
  const instance = axios.create({
    baseURL: baseUrl
  })
  instance.interceptors.response.use(
    (res) => {
      if (res && res.status === 200) {
        return res.data
      }
    },
    (error) => {
      if (error.response) {
        switch (error.response.status) {
          case 404:
            break
          default:
            console.log(error.message)
        }
      }
      return Promise.reject(error)
    }
  )
  return instance
}
