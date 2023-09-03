import getAPI from '../api/getAPI'
import Hello from './Hello'

const App = () => {
  const { data } = getAPI()
  return (
    <>
      <Hello></Hello>
      <div>title:{data.msg}</div>
    </>
  )
}
export default App
