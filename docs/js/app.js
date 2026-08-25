const storageKey = 'senai-suporte-tickets';
const list = document.querySelector('#ticket-list');
const state = { tickets: JSON.parse(localStorage.getItem(storageKey) || '[]') };
const labels = { OPEN:'Aberto', IN_PROGRESS:'Em atendimento', RESOLVED:'Resolvido' };
const esc = value => String(value).replace(/[&<>'"]/g, character => ({ '&':'&amp;', '<':'&lt;', '>':'&gt;', "'":'&#39;', '"':'&quot;' })[character]);
function persist() { localStorage.setItem(storageKey, JSON.stringify(state.tickets)); }
function render() {
  const query = document.querySelector('#search').value.toLowerCase();
  const filter = document.querySelector('#status-filter').value;
  const tickets = state.tickets.filter(ticket => (filter === 'ALL' || ticket.status === filter) && `${ticket.title} ${ticket.requester}`.toLowerCase().includes(query));
  list.innerHTML = tickets.map(ticket => `<tr><td><b>${esc(ticket.title)}</b><small>#${ticket.id}</small></td><td>${esc(ticket.requester)}</td><td>${esc(ticket.priority)}</td><td><span class="badge ${ticket.status}">${labels[ticket.status]}</span></td><td>${new Intl.DateTimeFormat('pt-BR').format(new Date(ticket.createdAt))}</td><td><select class="action-select" data-id="${ticket.id}" aria-label="Alterar status"><option value="">Ações</option><option value="OPEN">Aberto</option><option value="IN_PROGRESS">Em atendimento</option><option value="RESOLVED">Resolvido</option></select><button class="delete-button" data-delete="${ticket.id}" aria-label="Excluir chamado">×</button></td></tr>`).join('');
  document.querySelector('#empty-state').hidden = tickets.length > 0;
  ['OPEN', 'IN_PROGRESS', 'RESOLVED'].forEach(status => document.querySelector(`#${({ OPEN:'open', IN_PROGRESS:'progress', RESOLVED:'resolved' })[status]}-count`).textContent = state.tickets.filter(ticket => ticket.status === status).length);
  document.querySelector('#nav-count').textContent = state.tickets.length;
}
list.addEventListener('change', event => { if (!event.target.dataset.id) return; const ticket = state.tickets.find(item => item.id === Number(event.target.dataset.id)); if (ticket && event.target.value) { ticket.status = event.target.value; persist(); render(); } });
list.addEventListener('click', event => { const id = event.target.dataset.delete; if (id) { state.tickets = state.tickets.filter(ticket => ticket.id !== Number(id)); persist(); render(); } });
document.querySelector('#search').addEventListener('input', render);
document.querySelector('#status-filter').addEventListener('change', render);
document.querySelector('#new-ticket-button').addEventListener('click', () => document.querySelector('#ticket-dialog').showModal());
document.querySelector('#clear-data').addEventListener('click', () => { state.tickets = []; persist(); render(); });
document.querySelector('#ticket-form').addEventListener('submit', event => { event.preventDefault(); const data = Object.fromEntries(new FormData(event.target)); state.tickets.unshift({ ...data, id: Date.now(), status: 'OPEN', createdAt: new Date().toISOString() }); persist(); render(); event.target.reset(); document.querySelector('#ticket-dialog').close(); });
render();