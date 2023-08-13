beforeEach(() => {
  cy.setupIntercept()
})

describe('Modal E2E Test', () => {
  it('should open modal, input room name, send POST request, and verify response', () => {
    cy.visit('http://localhost:3001/rooms')

    cy.get('.navbar__btn').click()

    // 顯示modal
    cy.get('.custom-modal').should('be.visible')

    // 輸入欄位輸入'一起玩富饒之城'
    cy.get('#roomName').type('一起玩富饒之城')

    // 按下確認按鈕
    cy.get('.blue-btn').click()

    // 創建房間POST應該拿到statusOK和200
    cy.wait('@createRoomRequest').should(({ request, response }) => {
      expect(response.statusCode).to.equal(200)
      expect(response.body.status).to.equal('OK')
    })
  })
})
