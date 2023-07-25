// eslint-disable-next-line no-undef
describe('E2E Tests to MockAPI', () => {
  // eslint-disable-next-line no-undef
  beforeEach(() => {
    // 跑E2E測試時，intercept時會攔截XHR、fetch、跨域請求，將get方法呼叫的API改為放在fixtures資料夾的hello.json*
    // eslint-disable-next-line no-undef
    cy.intercept('GET', 'https://001f08b9-acb7-4c3a-a54f-a9254b7e8e55.mock.pstmn.io/get?msg=hello', {
      fixture: 'hello.json'
    }).as('getData')
  })

  // eslint-disable-next-line no-undef
  it('should load data from Local Mock API', () => {
    // eslint-disable-next-line no-undef
    cy.visit('http://localhost:3001/')

    // eslint-disable-next-line no-undef
    cy.wait('@getData')
      .its('response.body')
      .then((data) => {
        // 在這裡設定預期從API獲取到的數據
        // eslint-disable-next-line no-undef
        expect(data.args.msg).to.equal('hello')
      })
  })
})
