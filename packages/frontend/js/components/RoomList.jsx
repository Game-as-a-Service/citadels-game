import { useEffect, useState } from 'react'
import Pagination from './Pagination'
import { useDispatch, useSelector } from 'react-redux'
import { getRoomList } from '../redux/slice/roomSlice'

const RoomList = () => {
  const dispatch = useDispatch()
  const { data, loading } = useSelector((state) => state.room)
  const { rooms, totalRoom } = data || {}
  const isOdd = (totalAmout) => totalAmout % 2 !== 0
  // pagination
  const [pagination, setPagination] = useState({
    totalPage: 1,
    current: 1,
    pageSize: 6
  })
  const { totalPage, current, pageSize } = pagination
  useEffect(() => {
    if (totalRoom > 0) {
      const totalPage = Math.ceil(totalRoom / pageSize)
      setPagination((prev) => ({ ...prev, totalPage }))
    }
  }, [totalRoom])
  const getData = () => {
    dispatch(getRoomList())
  }
  const onPrevClick = () => {
    setPagination((prev) => ({ ...prev, current: current - 1 }))
  }
  const onNextClick = () => {
    setPagination((prev) => ({ ...prev, current: current + 1 }))
  }
  const onPageClick = (page) => {
    setPagination((prev) => ({ ...prev, current: page }))
  }
  // data
  const [dataSource, setDataSource] = useState(null)
  useEffect(() => {
    getData()
  }, [])
  useEffect(() => {
    if (rooms) {
      const end = pageSize * current
      const start = end - pageSize
      setDataSource(rooms.slice(start, end))
    }
  }, [current, rooms])
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
        {loading && <div>loading ...</div>}
        {dataSource ? (
          dataSource?.map((room, index) => (
            <div className='list__card' key={index}>
              <div className='host'>
                <div className='avatar'></div>
                <div className='name'>{room.holderName}</div>
              </div>
              <div className='room-name'>
                <div className='title'>房間名稱</div>
                <div className='detail'>{room.gameName}</div>
              </div>
              <div className='players'>
                <div className='title'>人數</div>
                <div className='detail'>{room.totalPlayers}</div>
              </div>
            </div>
          ))
        ) : (
          <div>查無資料</div>
        )}
        {dataSource && isOdd(dataSource?.length) && (
          <div className='list__card bg--trans'></div>
        )}
      </div>
      <div className='row__list'>
        {dataSource?.length > pageSize && (
          <Pagination
            className='row__pagination'
            current={current}
            totalPage={totalPage}
            onPrevClick={onPrevClick}
            onNextClick={onNextClick}
            onPageClick={onPageClick}
          />
        )}
      </div>
    </div>
  )
}

export default RoomList
