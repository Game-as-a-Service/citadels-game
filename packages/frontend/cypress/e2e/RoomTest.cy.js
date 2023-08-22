beforeEach(() => {
  cy.setupIntercept()
})

describe('Modal E2E Test', () => {
  //創建房間成功案例
  it('should open modal, input room name, send POST request, and verify response', () => {
    cy.visit('http://localhost:3001/rooms')

    cy.get('.navbar__btn').click()

    // 顯示modal
    cy.get('.modal-content').should('be.visible')

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

  it('should handle connection error and display error modal', () => {
    //創建房間失敗案例
    cy.setupInterceptWithError()

    cy.visit('http://localhost:3001/rooms')

    cy.get('.navbar__btn').click()

    cy.get('.modal-content').should('be.visible')

    cy.get('#roomName').type('一起玩富饒之城')

    cy.get('.blue-btn').click()

    cy.wait('@errorRequest')

    cy.get('.error-modal').should('be.visible')
  })
})
