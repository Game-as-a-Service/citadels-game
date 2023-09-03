import getAPI from '../api/getAPI'
import Hello from './Hello' // 請根據你的目錄結構修改路徑

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
