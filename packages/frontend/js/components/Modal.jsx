import React from 'react'
import PropTypes from 'prop-types'

const Modal = ({ isModalOpen, title, footer, children }) => {
  return (
    <>
      {isModalOpen && (
        <div className='modal-overlay'>
          <div className='modal-content'>
            <div className='modal-header'>{title}</div>
            <div className='modal-body'>{children}</div>
            <div className='modal-footer'>{footer}</div>
          </div>
        </div>
      )}
    </>
  )
}

Modal.propTypes = {
  isModalOpen: PropTypes.bool.isRequired,
  title: PropTypes.node,
  footer: PropTypes.node,
  children: PropTypes.node
}

export default Modal
