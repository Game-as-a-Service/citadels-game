import React from 'react'
import PropTypes from 'prop-types'

const Modal = (props) => {
  const { isModalOpen, modalContent } = props
  return (
    <>
      {isModalOpen && (
        <div className='custom-modal'>
          <div className='custom-modal-content'>{modalContent}</div>
        </div>
      )}
    </>
  )
}

Modal.propTypes = {
  isModalOpen: PropTypes.bool.isRequired,
  modalContent: PropTypes.node.isRequired
}

export default Modal
