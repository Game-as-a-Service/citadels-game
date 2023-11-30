import React from 'react'
import { useParams } from 'react-router-dom'
import { useState, useEffect } from 'react'
import { getSpecificRoom, leaveRoom } from '../api'
import Modal from './Modal'
import ErrorModal from './ErrorModal'
import { specific_room, GameData, PlayerView } from '../common/types'
const More = require('../../src/img/more.svg').default
const Leave = require('../../src/img/leaveGame.svg').default

import { useUser } from '../contexts/UserContext'
import { useNavigate } from 'react-router-dom'

export type room = {
  roomId: string
  roomName: string
  createTime: string
  status: string
  holderName: string
  holderId: string
  totalUsers: number
  users: Array<{
    userId: string
    userName: string
    userImage: string
  }>
}

const Game = () => {
  type Params = {
    roomId: string
  }
  const { roomId } = useParams<Params>() // 獲取路徑參數 roomId
  console.log(roomId)

  // 錯誤
  const [isErrorVisible, setIsErrorVisible] = useState<boolean>(false)
  const [errorText, setErrorText] = useState<String>('')

  // 玩家資訊
  const [roomInfo, setRoomInfo] = useState<specific_room | null>(null)
  const { user } = useUser()
  const [isHolder, setIsHolder] = useState<boolean>(false)

  // 遊戲開始
  const [isGameStart, setIsGameStart] = useState(false)
  const GameStart = () => {
    setIsGameStart(!isGameStart)
  }

  const [GameInfo, setGameInfo] = useState<PlayerView | null>(null)


  //SSE寫法
  useEffect(() => {
    initRoomData()
    const sse = new EventSource(
      `https://001f08b9-acb7-4c3a-a54f-a9254b7e8e55.mock.pstmn.io/event/citadels/rooms/${roomId}`
    )
    sse.onmessage = (e) => updateRoomData(e.data)

    sse.onerror = () => {
      sse.close()
    }
    isPlayerHolder()

    if(isGameStart){
      const sseGame = new EventSource(
        `https://001f08b9-acb7-4c3a-a54f-a9254b7e8e55.mock.pstmn.io/event/citadels/game/${roomId}`
      )
      sseGame.onmessage = (e) => updateGameData(e.data)

      sseGame.onerror = () => {
        sseGame.close()
      }
    }

    return () => {
      sse.close()
    }
  }, [isGameStart])

  const initRoomData = () => {
    if (roomId) {
      getSpecificRoom(roomId)
        .then((res) => {
          if (res.status.toString() === 'OK') {
            // @ts-ignore
            setRoomInfo(res.rooms)
            // @ts-ignore
            if (res.rooms.holderId === user?.userId) {
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
  }

  const updateRoomData = (data: any) => {
    const parsedData: specific_room = JSON.parse(data.rooms)
    setRoomInfo(parsedData)
  }

  const isPlayerHolder = () => {
    console.log(roomInfo?.holderId)
    console.log(user?.userId)
    if (roomInfo?.holderId === user?.userId) {
      setIsHolder(true)
    }
  }

  const navigate = useNavigate()
  const leaveGame = () => {
    if (roomId) {
      leaveRoom({ userId: user?.userId }, roomId).then((res) => {
        if (res.status.toString() === 'OK') {
          console.log('leave room success')
        }
      })
    }
    navigate(`/rooms`)
  }

  const [isModalOpen, setIsModalOpen] = useState<boolean>(false)
  const showModal = () => {
    setIsModalOpen(!isModalOpen)
  }


  //開始遊戲

  const updateGameData = (data: any) => {
    const parsedData: PlayerView = JSON.parse(data.playerViews)
    setGameInfo(parsedData)
  }
  return (
    <>
      <div className='game'>
        <nav className='nav'>
          <div className='nav_content'>
            <div className='leave' onClick={showModal}>
              <Leave></Leave>離開遊戲{isHolder && '/解散房間'}
            </div>
            <div className='nav_title'>{roomInfo?.roomName}</div>
          </div>
        </nav>
        <Modal
          isModalOpen={isModalOpen}
          title={<div>確定要離開房間嗎?</div>}
          footer={
            <>
              <button className='cancel-btn' onClick={showModal}>
                取消
              </button>
              <button className='blue-btn' onClick={leaveGame}>
                離開房間
              </button>
            </>
          }
        ></Modal>
        <section className='center'>
          <div className='user_section'>
            {roomInfo?.users?.map((user, index) => (
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
                      <span className='f-20-db'>0</span>
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
        </section>
      </div>
      <div className='my_game'>
        <div className='my_info'>
          <div className='avatar-lg'></div>
          <div className='my_name'>{user?.userName}</div>
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
                <span className='f-30-b'>0</span>
                <span className='f-20-b'>枚</span>
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
