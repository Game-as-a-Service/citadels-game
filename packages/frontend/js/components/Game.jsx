import { useParams } from 'react-router-dom'
import { useState, useEffect } from 'react'
import { getSpecificRoom } from '../api'
import More from '../../src/img/more.svg'
import LeaveGame from '../../src/img/leaveGame.svg'
import ErrorModal from './ErrorModal'
import { useAuth } from './AuthContext'

const Game = () => {
  const { roomId } = useParams() // 獲取路徑參數 roomId
  // console.log(roomId)

  // 錯誤
  const [isErrorVisible, setIsErrorVisible] = useState(false)
  const [errorText, setErrorText] = useState('')
  // 玩家資訊
  // const [usersList, setUsersList] = useState([])
  // const [roomName, setRoomName] = useState('')

  const [isHolder, setIsHolder] = useState(false)
  const [roomInfo, setRoomInfo] = useState({})
  const { myAuth } = useAuth()

  // 遊戲開始
  const [isGameStart, setIsGameStart] = useState(false)
  const GameStart = () => {
    setIsGameStart(!isGameStart)
  }
  const fetchData = () => {
    getSpecificRoom(roomId)
      .then((res) => {
        if (res.status === 'OK') {
          setRoomInfo(res.rooms)
          console.log('call Specific Room')
          if (res.rooms.holderName === myAuth.userName) {
            setIsHolder(true)
          }
        }
      })
      .catch((err) => {
        console.log(err)
        setIsErrorVisible(true)
        setErrorText('連線發生錯誤')
      })
  }

  useEffect(() => {
    fetchData()
    // 設置定時器，每隔3秒重新獲取房間人數數據
    // let intervalId
    // if (!isGameStart) {
    // intervalId = setInterval(() => {
    // }, 3000)
    // }
    // return () => {
    // clearInterval(intervalId)
    // }
  }, [roomId, isGameStart])
  return (
    <>
      <nav className='nav'>
        <div className='leaveIcon'>
          <LeaveGame />
          <span>離開遊戲</span>
        </div>
        <div className='nav_title'>{roomInfo.roomName}</div>
      </nav>
      <div className='game'>
        <div className='user_section'>
          {roomInfo.users?.map((user, index) => (
            <div className='user_panel' key={index}>
              <div className='user_info'>
                <div className='avatar-lg'></div>
                <div className='user_name'>{user.userName}</div>
              </div>
              <div className='user_asset'>
                <div className='asset'>
                  <div className='card_info'>
                    <div className='card_icon'></div>
                    <div className='f-12-b'>手牌</div>
                  </div>
                  <div className='card_nums'>
                    <span className='f-20-db'>5</span>
                    <span className='f-12-db'>張</span>
                  </div>
                </div>
                <div className='asset'>
                  <div className='card_info'>
                    <div className='money_icon'></div>
                    <div className='f-12-b'>金幣</div>
                  </div>
                  <div className='money_nums'>
                    <span className='f-20-db'>10</span>
                    <span className='f-12-db'>枚</span>
                  </div>
                </div>
              </div>
              <div className='area'></div>
              <div className='seemore'>
                <More />
                <div>
                  <span className='f-12-db'>已建立</span>
                </div>
                <div className='f-12-db'>
                  <span>0</span>
                  <span>個地區</span>
                </div>
              </div>
            </div>
          ))}
          {isGameStart && (
            <div className='my_area'>
              <div className='my_area_group'>
                <div className='area_text'>
                  <span className='f-14-r'>我的地區</span>
                </div>
                <div className='area'></div>
              </div>
              <div className='my_point'>
                <span className='f-30-r'>2分</span>
                <span className='f-14-r'>總計分數</span>
              </div>
            </div>
          )}
          {!isGameStart && (
            <div className='pending'>
              <span className='f-40-w'>...等待其他玩家加入</span>
            </div>
          )}
        </div>
      </div>
      <div className='my_game'>
        <div className='user_info'>
          <div className='avatar-lg'></div>
          <div className='user_name'>{myAuth.userName}</div>
        </div>
        {isGameStart && (
          <div className='my_game_info'>
            <div className='my_character'></div>
            <div className='my_card_group'>
              <div className='my_card'></div>
              <div className='my_card'></div>
              <div className='my_card'></div>
              <div className='my_card'></div>
            </div>
            <div className='my_money'>
              <div className='my_money_icon'></div>
              <div className='my_money_nums'>
                <span>0</span>
                <span>枚</span>
              </div>
            </div>
          </div>
        )}
        {!isGameStart && isHolder && (
          <div className='blue-btn' onClick={GameStart}>
            開始遊戲
          </div>
        )}
      </div>
      <ErrorModal
        isErrorVisible={isErrorVisible}
        onClose={() => setIsErrorVisible(false)}
        errorText={errorText}
      ></ErrorModal>
    </>
  )
}

export default Game
