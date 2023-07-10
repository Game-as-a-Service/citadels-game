import getAPI from '../api/getAPI'

const App = () => {
  const { data } = getAPI()
  return (
    <>
      <div>title:{data.msg}</div>
    </>
  )
}

export default App
