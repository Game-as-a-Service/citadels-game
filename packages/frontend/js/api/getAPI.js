import { useEffect, useState } from 'react'
import axios from 'axios'

function getAPI() {
  const [data, setData] = useState({})
  useEffect(() => {
    axios({
      method: 'GET',
      url: 'https://f51716f3-6393-4362-ac46-7e3ecffa5f7e.mock.pstmn.io/get?test=%E6%B8%AC%E8%A9%A6%E7%92%B0%E5%A2%83'
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
