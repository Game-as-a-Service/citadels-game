const Game = () => {
  return (
    <>
      <nav className='nav'>
        <div className='nav_title'>房間名稱</div>
      </nav>
      <div className='game'>
        <div className='player_info'>
          <div className='player_avatar'></div>
          <div className='player_asset'>
            <div className='asset_card'>
              <div className='card_icon'></div>
              <div className='card_nums'>
                <span>5</span>
                <span>張</span>
              </div>
            </div>
            <div className='asset_money'>
              <div className='money_icon'></div>
              <div className='money_nums'>
                <span>10</span>
                <span>枚</span>
              </div>
            </div>
          </div>
          <div className='area'></div>
          <div className='seemore'>
            <div>
              <span>已建立</span>
            </div>
            <div>
              <span>3</span>
              <span>個地區</span>
            </div>
          </div>
        </div>
        <div className='my_game'>
          <div className='player_avatar'></div>
          <div className='my_character'></div>
          <div className='my_card_group'>
            <div className='my_card'></div>
            <div className='my_card'></div>
            <div className='my_card'></div>
            <div className='my_card'></div>
          </div>
          <div className='my_money'>
            <div className='my_money_icon'></div>
            <div className='my_money_nums'>
              <span>10</span>
              <span>枚</span>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default Game
