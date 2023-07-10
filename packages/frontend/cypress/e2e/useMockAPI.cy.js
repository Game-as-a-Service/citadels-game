// eslint-disable-next-line no-undef
describe('E2E Tests to MockAPI', () => {
  // eslint-disable-next-line no-undef
  beforeEach(() => {
    // 跑E2E測試時，intercept時會攔截XHR、fetch、跨域請求，將get方法呼叫的API改為放在fixtures資料夾的hello.json*
    // eslint-disable-next-line no-undef
    cy.intercept(
      'GET',
      'https://f51716f3-6393-4362-ac46-7e3ecffa5f7e.mock.pstmn.io/get?test=%E6%B8%AC%E8%A9%A6%E7%92%B0%E5%A2%83',
      { fixture: 'hello.json' }
    ).as('getData')
  })

  // eslint-disable-next-line no-undef
  it('should load data from Local Mock API', () => {
    // eslint-disable-next-line no-undef
    cy.visit('/')

    // eslint-disable-next-line no-undef
    cy.wait('@getData')
      .its('response.body')
      .then((data) => {
        // 在這裡設定預期從API獲取到的數據
        // eslint-disable-next-line no-undef
        expect(data.msg).to.equal('hello')
      })
  })
})
