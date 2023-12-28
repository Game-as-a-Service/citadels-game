import React from 'react'
import PropTypes from 'prop-types'

interface Props {
  isUserInfoModalOpen: boolean
}

const UserInfoModal: React.FC<Props> = ({ isUserInfoModalOpen }) => {
  return (
    <>
      {isUserInfoModalOpen && (
        <div className='modal-overlay'>
          <div className='user-info-modal-content'>
            <div className='avatar-lg'></div>
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
            <div className='area-container'>
              <div className='area'></div>
              <div className='area'></div>
              <div className='area'></div>
              <div className='area'></div>
              <div className='area'></div>
            </div>
            <div className='btn-close'></div>
          </div>
        </div>
      )}
    </>
  )
}

UserInfoModal.propTypes = {
  isUserInfoModalOpen: PropTypes.bool.isRequired
}

export default UserInfoModal
