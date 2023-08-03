import { useState, useEffect } from 'react'
import Pagination from './Pagination'
import { useDispatch, useSelector } from 'react-redux'
import { getRoomList, createRoom } from '../redux/slice/roomSlice'
import Modal from './Modal'

const RoomList = () => {
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
    console.log('isModalOpen', isModalOpen)
    setIsModalOpen(!isModalOpen)
  }

  const [newRoom, setNewRoom] = useState({
    playerName: '',
    roomName: ''
  })

  const handleRoomNameChange = (e) => {
    setNewRoom({
      ...newRoom,
      [e.target.name]: e.target.value
    })
  }

  const handleSubmitRoomName = (event) => {
    event.preventDefault()
    console.log('roomName', newRoom)
    dispatch(createRoom(newRoom))
  }
  const modalContent = (
    <form className='createRoom'>
      <div>
        <label htmlFor='roomName' className='f-24-b'>
          房間名稱：
        </label>
        <input
          id='naroomNameme'
          type='text'
          name='roomName'
          value={newRoom.roomName}
          onChange={(e) => handleRoomNameChange(e)}
          placeholder='輸入房間名稱'
          className='main_input'
        />
      </div>

      <div className='button_group'>
        <button
          className='cancel-btn'
          onClick={() => {
            showModal()
          }}
        >
          取消
        </button>
        <button
          className='blue-btn'
          type='submit'
          onClick={(event) => {
            handleSubmitRoomName(event)
          }}
        >
          創建房間
        </button>
      </div>
    </form>
  )

  return (
    <div className='room-list'>
      <div className='navbar'>
        <div className='navbar__role'>
          <img className='avatar' />
          <span className='name'>王老先生</span>
        </div>
        <div
          className='navbar__btn'
          onClick={() => {
            showModal()
          }}
        >
          <span className='cross-btn'></span>
          <span className='text'>創建房間</span>
        </div>
      </div>
      <div className='row__list list'>
        {!data && <div>loading ...</div>}
        {data?.map((room, index) => (
          <div className='list__card' key={index}>
            <div className='avatar'></div>
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
      <Modal isModalOpen={isModalOpen} modalContent={modalContent}></Modal>
    </div>
  )
}

export default RoomList
