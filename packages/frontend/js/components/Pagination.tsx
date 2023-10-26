import { useState } from 'react'

// /**
//  * Render pagination
//  * @param {Object} props
//  * @param {number} props.totalPage - total page
//  * @param {number} props.current - current page
//  * @param {function} props.onPrevClick - callback triggers when click prev btn
//  * @param {function} props.onNextClick  - callback triggers when click next btn
//  * @param {function} props.onPageClick - callback triggers when click page btn
//  * @param {string} props.className - classname applies on wrapper of pagination
//  */

type Pagination = {
  totalPage: number
  current: number
  onPrevClick: Function
  onNextClick: Function
  onPageClick: Function
  className: string
}

const Pagination: React.FC<Pagination> = ({
  totalPage,
  current,
  onPrevClick,
  onNextClick,
  onPageClick,
  className = ''
}) => {
  const [currentPage, setCurrentPage] = useState<number>(current)

  const renderPrevTabs = (): React.ReactElement[] => {
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
  const renderNextTabs = (): React.ReactElement[] => {
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
  const handleClickPage = (page: number): void => {
    setCurrentPage(page)
    if (onPageClick) onPageClick(page)
  }
  const handleClickPrev = (): void => {
    if (currentPage > 1) {
      setCurrentPage((prev) => prev - 1)
      if (onPrevClick) onPrevClick()
    }
  }
  const handleClickNext = (): void => {
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
        <div key={currentPage} className='page-btn active'>
          {currentPage}
        </div>
      }
      {renderNextTabs()}
      <div key='next' className='next-btn' onClick={handleClickNext}></div>
    </div>
  )
}

export default Pagination
