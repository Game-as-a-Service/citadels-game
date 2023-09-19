import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import Pagination from './Pagination'
import { useDispatch, useSelector } from 'react-redux'
import { getRoomList } from '../redux/slice/roomSlice'
import { createRoom } from '../api'
import Modal from './Modal'
import ErrorModal from './ErrorModal'
import { useAuth } from './AuthContext'

const RoomList = () => {
  localStorage.setItem('userName', '陳XX') // 正式版要刪除
  localStorage.setItem('userImage', 'userImage') // 正式版要刪除

  const dispatch = useDispatch()
  const { data = [] } = useSelector((state) => state.room)
  const isOdd = (totalAmout) => totalAmout % 2 !== 0
  useEffect(() => {
    let init = true
    if (init) dispatch(getRoomList())
    return () => {
      init = false
    }
  }, [])

  // 創建房間
  const [isModalOpen, setIsModalOpen] = useState(false)
  const showModal = () => {
    setIsModalOpen(!isModalOpen)
  }

  const [newRoom, setNewRoom] = useState({
    roomName: '',
    userName: '',
    userImage: ''
  })

  const { myAuth } = useAuth()

  const handleRoomNameChange = (e) => {
    setNewRoom({
      ...newRoom,
      [e.target.name]: e.target.value,
      userName: myAuth.userName,
      userImage: myAuth.userImage
    })
  }

  const navigate = useNavigate()
  const [isErrorVisible, setIsErrorVisible] = useState(false)
  const [errorText, setErrorText] = useState('')

  const handleSubmitRoomName = (e) => {
    e.preventDefault()
    createRoom(newRoom)
      .then((res) => {
        if (res.status === 'OK') {
          const roomID = res.room.roomId
          navigate(`/game/${roomID}`)
          // TODO : 加入URLParamter
        }
      })
      .catch((err) => {
        console.log(err)
        setIsErrorVisible(true)
        setErrorText('連線發生錯誤')
      })
  }

  return (
    <div className='room-list'>
      <div className='navbar'>
        <div className='navbar__role'>
          <img className='avatar-sm' />
          <span className='name'>王老先生</span>
        </div>
        <div className='navbar__btn' onClick={showModal}>
          <span className='cross-btn'></span>
          <span className='text'>創建房間</span>
        </div>
      </div>
      <div className='row__list list'>
        {!data && <div>loading ...</div>}
        {data?.map((room, index) => (
          <div className='list__card' key={index}>
            <div className='avatar-sm'></div>
            <div className='host'>
              <div className='title'>房主</div>
              <div className='detail'>{room.holderName}</div>
            </div>
            <div className='players'>
              <div className='title'>人數</div>
              <div className='detail'>{room.totalPlayers}</div>
            </div>
          </div>
        ))}
        {isOdd(data?.length) && <div className='list__card bg--trans'></div>}
      </div>
      <div className='row__list'>
        <Pagination className='row__pagination' current={10} totalPage={20} />
      </div>
      <Modal
        isModalOpen={isModalOpen}
        title={null}
        footer={
          <div className='button_group'>
            <button className='cancel-btn' onClick={showModal}>
              取消
            </button>
            <button
              className='blue-btn'
              type='submit'
              onClick={(e) => {
                handleSubmitRoomName(e)
              }}
            >
              創建房間
            </button>
          </div>
        }
      >
        <form>
          <div>
            <label htmlFor='roomName' className='f-24-b'>
              房間名稱：
            </label>
            <input
              id='roomName'
              type='text'
              name='roomName'
              value={newRoom.roomName}
              onChange={(e) => handleRoomNameChange(e)}
              placeholder='輸入房間名稱'
              className='main_input'
            />
          </div>
        </form>
      </Modal>
      <ErrorModal
        isErrorVisible={isErrorVisible}
        onClose={() => setIsErrorVisible(false)}
        errorText={errorText}
      ></ErrorModal>
    </div>
  )
}

export default RoomList
