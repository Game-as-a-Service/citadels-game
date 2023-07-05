import React from 'react'

const Login = () => {
  return (
    <>
      <section className='login'>
        <span className='f-40-w'>WelCome to Citadels Game !</span>
        <form className='login_borad'>
          <div className='login_imgwrap'>
            <img src='' />
          </div>
          <div className='f-20-b'>換一個</div>
          <label htmlFor='name' className='f-24-b'>
            請輸入你的名字：
          </label>
          <input
            id='name'
            type='text'
            name='name'
            placeholder='輸入名字'
            className='login_input'
          />
        </form>
        <button className='login_button'>進入遊戲</button>
      </section>
    </>
  )
}

export default Login
