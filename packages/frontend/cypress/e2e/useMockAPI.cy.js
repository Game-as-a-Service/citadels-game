describe('E2E Tests to MockAPI', () => {
  beforeEach(() => {
    // 跑E2E測試時，intercept時會攔截XHR、fetch、跨域請求，將get方法呼叫的API改為放在fixtures資料夾的hello.json*
    cy.intercept(
      'GET',
      'https://001f08b9-acb7-4c3a-a54f-a9254b7e8e55.mock.pstmn.io/get?msg=hello',
      {
        fixture: 'hello.json'
      }
    ).as('getAPI')
  })

  it('should load data from Local Mock API', () => {
    cy.visit('http://localhost:3001/')

    cy.wait('@getAPI', { timeout: 10000 })
      .its('response.body')
      .then((data) => {
        // 在這裡設定預期從API獲取到的數據
        expect(data.args.msg).to.equal('hello')
      })
  })
})
