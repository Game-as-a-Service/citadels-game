import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import RecycleSvg from '../../src/img/recycle.svg'
import Portrait1 from '../../src/img/portrait1.svg'
import Portrait2 from '../../src/img/portrait2.svg'

const Login = () => {
  // 有名子的話直接進入大廳
  const navigate = useNavigate()
  useEffect(() => {
    if (localStorage.getItem('userName')?.trim()) {
      navigate('/rooms')
    }
  }, [])

  // 更換圖片
  const portraits = [
    <dev key='1'>
      <Portrait1 />
    </dev>,
    <dev key='2'>
      <Portrait2 />
    </dev>
  ]
  const [portraitChangeCount, setPortraitChangeCount] = useState(0)
  const [portraitIndex, setPortraitIndex] = useState(0)
  const changeImage = () => {
    setPortraitChangeCount(portraitChangeCount + 1)
    setPortraitIndex(portraitChangeCount % portraits.length)
  }

  // 輸入名子
  const [name, setName] = useState('')
  const handleNameChange = (event) => {
    setName(event.target.value)
  }

  // 進入大廳
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
          <div className='login_imgwrap'>{portraits[portraitIndex]}</div>
          <div className='f-20-b'>
            <dev onClick={changeImage}>
              換一個 <RecycleSvg />
            </dev>
          </div>
          <label htmlFor='name' className='f-24-b'>
            輸入你的名字：
          </label>
          <input
            id='name'
            type='text'
            name='name'
            placeholder=''
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
