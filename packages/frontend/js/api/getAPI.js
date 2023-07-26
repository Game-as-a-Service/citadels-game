import { useEffect, useState } from 'react'
import axios from 'axios'

function getAPI() {
  const [data, setData] = useState({})
  useEffect(() => {
    axios({
      method: 'GET',
      url: 'https://001f08b9-acb7-4c3a-a54f-a9254b7e8e55.mock.pstmn.io/get?msg=hello'
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
