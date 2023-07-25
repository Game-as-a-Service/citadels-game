import { useEffect } from 'react'
import Pagination from './Pagination'
import { useDispatch, useSelector } from 'react-redux'
import { getRoomList } from '../redux/slice/roomSlice'

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
  return (
    <div className='room-list'>
      <div className='navbar'>
        <div className='navbar__role'>
          <img className='avatar' />
          <span className='name'>王老先生</span>
        </div>
        <div className='navbar__btn'>
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
    </div>
  )
}

export default RoomList
