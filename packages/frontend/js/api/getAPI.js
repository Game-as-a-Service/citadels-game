import { useEffect, useState } from 'react'
import axios from 'axios'

function getAPI() {
  const [data, setData] = useState({})
  useEffect(() => {
    axios({
      method: 'GET',
      url: 'https://postman-echo.com/get?msg=hello'
    })
      .then((res) => {
        setData(res.data.args)
      })
      .catch((error) => {
        console.log(error)
      })
  }, [])
  return { data }
}

export default getAPI
