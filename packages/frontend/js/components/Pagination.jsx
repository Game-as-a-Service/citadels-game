import { useEffect, useState } from 'react'

/**
 * Render pagination
 * @param {Object} props
 * @param {number} props.totalPage - total page
 * @param {number} props.current - current page
 * @param {function} props.onPrevClick - callback triggers when click prev btn
 * @param {function} props.onNextClick  - callback triggers when click next btn
 * @param {string} props.className - classname applies on wrapper of pagination
 */

const Pagination = ({
  totalPage,
  current,
  onPrevClick,
  onNextClick,
  className = ''
}) => {
  const [currentPage, setCurrentPage] = useState(current)

  const renderPrevTabs = () => {
    const pages = []
    let count = 0
    const maxCount = 2
    for (let i = currentPage - 1; i >= 1; i--) {
      if (count === maxCount && i !== 1) {
        pages.push(
          <div key='f-ellipsis' className='ellipsis'>
            •••
          </div>
        )
        pages.push(
          <div key={1} onClick={() => handleClickPage(1)} className='page-btn'>
            1
          </div>
        )
        break
      } else {
        pages.push(
          <div key={i} onClick={() => handleClickPage(i)} className='page-btn'>
            {i}
          </div>
        )
        count++
      }
    }
    return pages.reverse()
  }
  const renderNextTabs = () => {
    const pages = []
    let count = 0
    const maxCount = 2
    for (let i = currentPage + 1; i <= totalPage; i++) {
      if (count === maxCount && i !== totalPage) {
        pages.push(
          <div key='f-ellipsis' className='ellipsis'>
            •••
          </div>
        )
        pages.push(
          <div
            key={totalPage}
            onClick={() => handleClickPage(totalPage)}
            className='page-btn'
          >
            {totalPage}
          </div>
        )
        break
      } else {
        pages.push(
          <div key={i} onClick={() => handleClickPage(i)} className='page-btn'>
            {i}
          </div>
        )
        count++
      }
    }
    return pages
  }
  const handleClickPage = (page) => {
    setCurrentPage(page)
  }
  const handleClickPrev = () => {
    if (currentPage > 1) {
      setCurrentPage((prev) => prev - 1)
      if (onPrevClick) onPrevClick()
    }
  }
  const handleClickNext = () => {
    if (currentPage < totalPage) {
      setCurrentPage((prev) => prev + 1)
      if (onNextClick) onNextClick()
    }
  }
  return (
    <div className={'pagination ' + className}>
      <div key='prev' className='prev-btn' onClick={handleClickPrev}></div>
      {renderPrevTabs()}
      {
        <div
          key={currentPage}
          onClick={() => handleClickPage(currentPage)}
          className='page-btn active'
        >
          {currentPage}
        </div>
      }
      {renderNextTabs()}
      <div key='next' className='next-btn' onClick={handleClickNext}></div>
    </div>
  )
}

export default Pagination

Pagination.propTypes = {
  totalPage: PropTypes.number,
  current: PropTypes.number,
  onPrevClick: PropTypes.func,
  onNextClick: PropTypes.func,
  className: PropTypes.string
}
