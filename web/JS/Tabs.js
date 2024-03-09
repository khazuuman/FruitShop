const $ = document.querySelector.bind(document)
const $$ = document.querySelectorAll.bind(document)

const tabs = $$('.tab-item')
const panes = $$('.tab-pane')

tabs.forEach((tab, index) => {
    const pane = panes[index]

    tab.onclick = function () {

        const activeTab = $('.tab-item.active')
        const activePane = $('.tab-pane.active')

        if (activeTab) {
            activeTab.classList.remove('active')
        }
        if (activePane) {
            activePane.classList.remove('active')
            activePane.style.display = 'none'
        }

        this.classList.add('active')
        pane.classList.add('active')
        pane.style.display = 'block'
    }
})