import React from 'react'

function Character() {

const characterList = [
  {"id":1,"name":"殺手","skill":"莉莉絲的技能","skillDescription":"莉莉絲的技能描述","img":"killer.png"},{"id":2,"name":"莉莉絲","skill":"莉莉絲的技能","skillDescription":"莉莉絲的技能描述"},{"id":3,"name":"莉莉絲","skill":"莉莉絲的技能","skillDescription":"莉莉絲的技能描述"},{"id":4,"name":"莉莉絲","skill":"莉莉絲的技能","skillDescription":"莉莉絲的技能描述"},{"id":5,"name":"莉莉絲","skill":"莉莉絲的技能","skillDescription":"莉莉絲的技能描述"},{"id":6,"name":"莉莉絲","skill":"莉莉絲的技能","skillDescription":"莉莉絲的技能描述"},{"id":7,"name":"莉莉絲","skill":"莉莉絲的技能","skillDescription":"莉莉絲的技能描述"},{"id":8,"name":"莉莉絲","skill":"莉莉絲的技能","skillDescription":"莉莉絲的技能描述"}]

  return (
<>
      <div className='modal-overlay'>
        <div className='character-modal-content'>
          <div className='modal-header'>
            <span>請選擇本回合擔任角色</span>
            <span>雙擊可查看角色詳細技能</span>
          </div>
          <div className='character-modal-body'>
            {characterList.map((character) => {
              return (
                <div className='character-card' key={character.id}>
                  <div className='character-img'>
                    {/* <img src={character.img} alt={character.name} /> */}
                  </div>
                  <div className='character-name'>
                    {character.name}
                  </div>
                  <div className='character-skill'>
                    {character.skill}
                  </div>
                  <div className='character-skill-description'>
                    {character.skillDescription}
                  </div>
                </div>
              );
            })}
          </div>
          <div className='modal-footer'></div>
        </div>
      </div>
    </>
    
  )
}

export default Character