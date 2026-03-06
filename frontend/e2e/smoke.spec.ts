import {expect, test} from '@playwright/test';

test.describe('Smoke', () => {
    test('opens main pages', async ({page}) => {
        await page.goto('/');
        await expect(page.getByRole('link', {name: 'Главная'})).toBeVisible();

        await page.goto('/relays');
        await expect(page.getByText('Реле', {exact: false})).toBeVisible();

        await page.goto('/stations');
        await expect(page.getByRole('heading', {name: 'Станции'})).toBeVisible();
    });
});
