import '../src/scss/index.scss'
import { Provider } from 'react-redux'
import store from './redux/store'
import * as ReactDOM from 'react-dom/client'
import Routers from './routers'

ReactDOM.createRoot(document.getElementById('app')).render(
  <Provider store={store}>
    <Routers></Routers>
  </Provider>
)
