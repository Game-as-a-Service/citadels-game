import React from 'react'
import PropTypes from 'prop-types'

const ErrorModal = (props) => {
  const { isErrorVisible, onClose, errorText } = props
  return (
    <>
      {isErrorVisible && (
        <div className='error-modal'>
          <div className='error-modal-content'>
            <span className='error-text'>{errorText}</span>
            <button className='cancel-btn' onClick={onClose}>
              Close
            </button>
          </div>
        </div>
      )}
    </>
  )
}

ErrorModal.propTypes = {
  isErrorVisible: PropTypes.bool.isRequired,
  onClose: PropTypes.func.isRequired,
  errorText: PropTypes.node.isRequired
}

export default ErrorModal
