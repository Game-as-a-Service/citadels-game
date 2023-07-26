import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'

const Login = () => {
  const [name, setName] = useState('')
  const navigate = useNavigate()

  const handleNameChange = (event) => {
    setName(event.target.value)
  }

  const handleLogin = () => {
    console.log('userName:', name)
    localStorage.setItem('userName', name)
    // TODO: game lobby page not completed
    navigate('/rooms')
  }

  return (
    <>
      <section className='login'>
        <span className='f-40-w'>WelCome to Citadels Game !</span>
        <div className='login_borad' onSubmit={handleLogin}>
          <div className='login_imgwrap'>
            <img src='' />
          </div>
          <div className='f-20-b'>換一個</div>
          <label htmlFor='name' className='f-24-b'>
            輸入你的名字：
          </label>
          <input
            id='name'
            type='text'
            name='name'
            placeholder='輸入名字'
            className='login_input'
            value={name}
            onChange={handleNameChange}
          />
        </div>
        <button className='login_button' onClick={handleLogin}>
          進入遊戲
        </button>
      </section>
    </>
  )
}

export default Login
