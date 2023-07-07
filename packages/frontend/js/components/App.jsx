import getAPI from '../api/getAPI'

const App = () => {
  const { data } = getAPI()
  return (
    <>
      <div>title:{data.test}</div>
    </>
  )
}

export default App
