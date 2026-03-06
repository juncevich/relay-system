export type VerificationStatus = 'ok' | 'warning' | 'overdue' | 'unknown';

const OVERDUE_DAYS = 365;
const WARNING_DAYS = 270;

export function getVerificationStatus(date?: string): VerificationStatus {
    if (!date) return 'unknown';
    const diffDays = (Date.now() - new Date(date).getTime()) / 86_400_000;
    if (diffDays > OVERDUE_DAYS) return 'overdue';
    if (diffDays > WARNING_DAYS) return 'warning';
    return 'ok';
}

export const VERIFICATION_STATUS: Record<VerificationStatus, { label: string; color: string }> = {
    ok: {label: 'Проверено', color: '#4ade80'},
    warning: {label: 'Скоро проверка', color: '#fbbf24'},
    overdue: {label: 'Просрочено', color: '#f87171'},
    unknown: {label: 'Не проверено', color: '#56708a'},
};
